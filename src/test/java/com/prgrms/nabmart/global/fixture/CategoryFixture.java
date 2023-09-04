package com.prgrms.nabmart.global.fixture;

import com.prgrms.nabmart.domain.category.MainCategory;
import com.prgrms.nabmart.domain.category.SubCategory;
import com.prgrms.nabmart.domain.category.controller.request.RegisterMainCategoryRequest;
import com.prgrms.nabmart.domain.category.controller.request.RegisterSubCategoryRequest;
import com.prgrms.nabmart.domain.category.service.request.RegisterMainCategoryCommand;
import com.prgrms.nabmart.domain.category.service.request.RegisterSubCategoryCommand;

public final class CategoryFixture {

    private static final String MAIN_CATEGORY_NAME = "대카테고리";
    private static final String SUB_CATEGORY_NAME = "대카테고리";
    private static final Long MAIN_CATEGORY_ID = 1L;

    private CategoryFixture() {
    }

    public static MainCategory mainCategory() {
        return new MainCategory(MAIN_CATEGORY_NAME);
    }

    public static SubCategory subCategory(MainCategory mainCategory) {
        return new SubCategory(mainCategory, SUB_CATEGORY_NAME);
    }

    public static RegisterSubCategoryRequest registerSubCategoryRequest() {
        return new RegisterSubCategoryRequest(MAIN_CATEGORY_ID, SUB_CATEGORY_NAME);
    }

    public static RegisterSubCategoryCommand registerSubCategoryCommand() {
        return new RegisterSubCategoryCommand(MAIN_CATEGORY_ID, SUB_CATEGORY_NAME);
    }

    public static RegisterMainCategoryRequest registerMainCategoryRequest() {
        return new RegisterMainCategoryRequest(MAIN_CATEGORY_NAME);
    }

    public static RegisterMainCategoryCommand registerMainCategoryCommand() {
        return new RegisterMainCategoryCommand(MAIN_CATEGORY_NAME);
    }
}
