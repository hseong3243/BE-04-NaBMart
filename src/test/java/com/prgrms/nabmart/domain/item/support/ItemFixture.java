package com.prgrms.nabmart.domain.item.support;

import com.prgrms.nabmart.domain.category.MainCategory;
import com.prgrms.nabmart.domain.category.SubCategory;
import com.prgrms.nabmart.domain.item.Item;
import com.prgrms.nabmart.domain.item.LikeItem;
import com.prgrms.nabmart.domain.item.controller.request.RegisterLikeItemRequest;
import com.prgrms.nabmart.domain.item.service.request.DeleteLikeItemCommand;
import com.prgrms.nabmart.domain.item.service.request.FindLikeItemsCommand;
import com.prgrms.nabmart.domain.user.User;
import com.prgrms.nabmart.domain.item.ItemSortType;
import com.prgrms.nabmart.domain.item.service.request.FindItemsByMainCategoryCommand;
import com.prgrms.nabmart.domain.item.service.response.FindItemsResponse;
import com.prgrms.nabmart.domain.item.service.response.FindItemsResponse.FindItemResponse;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemFixture {

    private static final Long ITEM_ID = 1L;
    private static final String NAME = "아이템이름";
    private static final int PRICE = 1000;
    private static final String DESCRIPTION = "아이템설명";
    private static final int QUANTITY = 10;
    private static final int DISCOUNT = 0;
    private static final int REVIEW_COUNT = 0;
    private static final int LIKE_COUNT = 0;
    private static final int RATE = 0;
    private static final int MAX_QUANTITY = 10;
    private static final Long USER_ID = 1L;
    private static final Long LIKE_ITEM_ID = 1L;
    private static final String ITEM_SORT_TYPE = ItemSortType.NEW.name();

    public static Item item(MainCategory mainCategory, SubCategory subCategory) {
        return new Item(NAME, PRICE, DESCRIPTION, QUANTITY, DISCOUNT, MAX_QUANTITY, mainCategory,
            subCategory);
    }

    public static LikeItem likeItem(User user, Item item) {
        return new LikeItem(user, item);
    }

    public static FindItemsResponse findItemsResponse() {
        return new FindItemsResponse(List.of(findItemResponse()));
    }

    public static FindItemResponse findItemResponse() {
        return new FindItemsResponse.FindItemResponse(1L, NAME, PRICE, DISCOUNT, REVIEW_COUNT,
            LIKE_COUNT,
            RATE);
    }

    public static FindItemsByMainCategoryCommand findItemsByMainCategoryCommand(
        String mainCategoryName) {
        return FindItemsByMainCategoryCommand.of(10L, mainCategoryName, 3, ITEM_SORT_TYPE);
    }


    public static RegisterLikeItemRequest registerLikeItemRequest() {
        return new RegisterLikeItemRequest(ITEM_ID);
    }

    public static DeleteLikeItemCommand deleteLikeItemCommand() {
        return new DeleteLikeItemCommand(USER_ID, LIKE_ITEM_ID);
    }

    public static FindLikeItemsCommand findLikeItemsCommand() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return new FindLikeItemsCommand(USER_ID, pageRequest);
    }
}
