package com.prgrms.nabmart.domain.cart.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.prgrms.nabmart.domain.cart.Cart;
import com.prgrms.nabmart.domain.cart.CartItem;
import com.prgrms.nabmart.domain.cart.repository.CartItemRepository;
import com.prgrms.nabmart.domain.cart.repository.CartRepository;
import com.prgrms.nabmart.domain.cart.service.request.RegisterCartItemCommand;
import com.prgrms.nabmart.domain.category.MainCategory;
import com.prgrms.nabmart.domain.category.SubCategory;
import com.prgrms.nabmart.domain.item.domain.Item;
import com.prgrms.nabmart.domain.item.repository.ItemRepository;
import com.prgrms.nabmart.domain.user.User;
import com.prgrms.nabmart.domain.user.repository.UserRepository;
import com.prgrms.nabmart.global.fixture.CartFixture;
import com.prgrms.nabmart.global.fixture.CartItemFixture;
import com.prgrms.nabmart.global.fixture.CategoryFixture;
import com.prgrms.nabmart.global.fixture.ItemFixture;
import com.prgrms.nabmart.global.fixture.UserFixture;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartItemServiceTest {

    @InjectMocks
    private CartItemService cartItemService;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    User givenUser;
    Cart givenCart;
    Item givenItem;
    MainCategory givenMainCategory;
    SubCategory givenSubCategory;
    CartItem givenCartItem;
    int givenQuantity;

    @BeforeEach
    void setUp() {
        givenUser = UserFixture.user();
        givenCart = CartFixture.cart(givenUser);
        givenMainCategory = CategoryFixture.mainCategory();
        givenSubCategory = CategoryFixture.subCategory(givenMainCategory);
        givenItem = ItemFixture.item(givenMainCategory, givenSubCategory);
        givenQuantity = 10;
        givenCartItem = CartItemFixture.cartItem(givenCart, givenItem, givenQuantity);
    }

    @Nested
    @DisplayName("장바구니 상품 생성 Service 실행 시")
    class RegisterCartItemTest {

        RegisterCartItemCommand registerCartItemCommand = RegisterCartItemCommand.of(1L, 1L,
            givenQuantity);

        @Test
        @DisplayName("성공")
        void success() {
            // given
            given(userRepository.findById(any())).willReturn(Optional.ofNullable(givenUser));
            given(itemRepository.findById(any())).willReturn(Optional.ofNullable(givenItem));
            given(cartRepository.findByUser(any())).willReturn(Optional.ofNullable(givenCart));
            given(cartItemRepository.save(any())).willReturn(givenCartItem);

            // when
            cartItemService.registerCartItem(registerCartItemCommand);

            // then
            then(cartItemRepository).should().save(any());
        }
    }

    @Nested
    @DisplayName("장바구니 상품 삭제 Service 실행 시")
    class DeleteCartItemTest {

        @Test
        @DisplayName("성공")
        void success() {

            // given
            Long cartItemId = 1L;

            given(cartItemRepository.findById(any())).willReturn(
                Optional.ofNullable(givenCartItem));

            // when
            cartItemService.deleteCartItem(cartItemId);

            // then
            then(cartItemRepository).should().delete(any());
        }
    }
}
