package TrelloTest;

import TrelloTest.models.BoardModel;
import TrelloTest.models.CardModel;
import TrelloTest.models.ListModel;
import TrelloTest.utils.Helper;
import client.TrelloClient;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloTest {

    private BoardModel createdBoard;
    private ListModel createdList;
    private CardModel selectedCard;

    @Test
    @Order(1)
    public void createBoard() {
        Response res = TrelloClient.request()
                .body("{\"name\": \"test_board\"}")
                .post("boards");

        BoardModel boardPage = res.as(BoardModel.class);

        System.out.println(boardPage);
        Assert.assertEquals(res.getStatusCode(), 200);
        createdBoard = boardPage;
    }

    @Test
    @Order(2)
    public void createListOnBoard() {
        Response res = TrelloClient.request()
                .body("{\"name\": \"test_list\", \"idBoard\": \""+createdBoard.getId()+"\"}")
                .post("lists");

        ListModel listPage = res.as(ListModel.class);

        System.out.println(listPage);
        Assert.assertEquals(res.getStatusCode(), 200);
        createdList = listPage;
    }

    @Test
    @Order(3)
    public void createCards() {
        int cardCount = 2;

        for (int i = 0; i < cardCount; i++) {
            Response res = TrelloClient.request()
                    .body("{\"name\": \"card_"+i+"\", \"idList\": \""+createdList.getId()+"\"}")
                    .post("cards");

            CardModel cardPage = res.as(CardModel.class);
            System.out.println(cardPage);
            Assert.assertEquals(res.getStatusCode(), 200);
        }
    }

    @Test
    @Order(4)
    public void selectRandomCard() {
        Response res = TrelloClient.request()
                .get("lists/" + createdList.getId() + "/cards");

        CardModel[] cards = res.as(CardModel[].class);
        CardModel card = cards[Helper.randomNumber(0, 1)];
        System.out.println(card);
        Assert.assertEquals(res.getStatusCode(), 200);
        selectedCard = card;
    }

    @Test
    @Order(5)
    public void updateCardData() {
        Response res = TrelloClient.request()
                .body("{\"name\": \"card_"+selectedCard.getId()+"_updated\"}")
                .put("card/" + selectedCard.getId());

        CardModel card = res.as(CardModel.class);
        System.out.println(card);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test
    @Order(6)
    public void deleteCards() {
        Response res = TrelloClient.request()
                .get("lists/" + createdList.getId() + "/cards");

        CardModel[] cards = res.as(CardModel[].class);

        for (CardModel card : cards) {
            res = TrelloClient.request()
                    .delete("cards/" + card.getId());
            Assert.assertEquals(res.getStatusCode(), 200);
        }
    }

    @Test
    @Order(7)
    public void deleteBoard() {
        Response res = TrelloClient.request()
                .delete("boards/" + createdBoard.getId());

        System.out.println(res.getBody().asString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

}
