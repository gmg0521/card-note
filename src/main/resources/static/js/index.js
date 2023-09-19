let index = {
  init: function () {
    $("#save").on("click", () => {
      this.save();
    });
    $(".card").on("click", (event) => {
      let data = {
        cardId: $(event.currentTarget).find("#tempCardId").val(),
        title: $(event.currentTarget).find(".card-title").text(),
        content: $(event.currentTarget).find(".card-text").text(),
      };
      this.setCard(data);
    });
    $("#addCard").on("click", () => {
      this.setCard({ cardId: "", title: "", content: "" });
    });

    $("#search").on("input", () => {
      let data = {
        userId: $("#user_id").text(),
        title: $("#search").val(),
      };
      this.findCard(data);
    });
  },

  save: () => {
    let card = {
      cardId: $("#cardId").val(),
      userId: $("#user_id").text(),
      title: $("#cardTitle").val(),
      content: $("#cardContent").val(),
    };
    if (card.cardId == "") {
      $.ajax({
        type: "POST",
        url: "/api/cards/save",
        data: JSON.stringify(card),
        dataType: "JSON",
        contentType: "application/json; charset=utf-8",
      })
        .done((resp) => {
          if (resp.data == 1) {
            alert("카드를 저장하였습니다!");
            location.href = "/";
          }
        })
        .fail((error) => {
          console.log(error);
        });
    } else {
      $.ajax({
        type: "PUT",
        url: "/api/cards/" + card.cardId,
        data: JSON.stringify(card),
        dataType: "JSON",
        contentType: "application/json; charset=utf-8",
      })
        .done((resp) => {
          console.log(resp);
        })
        .fail((error) => {
          console.log(error);
        });
    }
  },
  setCard: (data) => {
    $("#cardId").val(data.cardId);
    $("#cardTitle").val(data.title);
    $("#cardContent").val(data.content);
  },
  findCard: (data) => {
    if (data.title != "") {
      $.ajax({
        type: "GET",
        url: "/api/cards/?userid=" + data.userId + "&title=" + data.title,
      }).done((resp) => {
        reloadCard(resp.data);
      });
    } else {
      $.ajax({
        type: "GET",
        url: "/api/cards/?userid=" + data.userId,
      }).done((resp) => {
        reloadCard(resp.data);
      });
    }
  },
};

function reloadCard(cardList) {
  $(".row").text("");
  let html;
  // 검색어는 있는데 발견할 수 없을 때
  if (cardList.length == 0) {
    html = `<p class="no-card text-center">
        검색 결과가 없습니다 ㅠㅠ
      </p>`;
    $(".row").append($(html));
  } else {
    cardList.forEach((card) => {
      const html = `<div class="col" data-bs-toggle="modal" data-bs-target="#cardModal">
    <div class="card">
      <div class="card-body">
        <input type="hidden" id="tempCardId" value="${card.id}">
        <h5 class="card-title">${card.title}</h5>
        <p class="card-text">${card.content}</p>
      </div>
    </div>
  </div>`;
      $(".row").append($(html));
    });
  }
  index.init();
}

$(function () {
  index.init();
});
