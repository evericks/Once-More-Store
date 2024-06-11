package com.oncemore.store.service.impl;

import com.oncemore.store.dto.CartDTO;
import com.oncemore.store.dto.CartItemDTO;
import com.oncemore.store.entity.CartItem;
import com.oncemore.store.entity.Product;
import com.oncemore.store.model.UserCartInfo;
import com.oncemore.store.repository.CartItemRepository;
import com.oncemore.store.repository.CartRepository;
import com.oncemore.store.repository.ProductRepository;
import com.oncemore.store.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserCartInfo userCartInfo;

    @Override
    @Transactional
    public String addProductToCart(UUID productId, int quantity) {
        String message = "";
//        Cart cart = cartRepository.findByUserId(userCartInfo.getCartId());
//        if (Objects.isNull(cart)) {
//            cart = new Cart();
//            cart.setUserId(userCartInfo.getUserId());
//            cart.setAmount(BigDecimal.ZERO);
//            cartRepository.saveAndFlush(cart);
//        }
        try {
        Product product = productRepository.getById(productId);
        int maxQuantity = product.getQuantity();
        if (maxQuantity < quantity) {
            return "Số lượng sản phẩm không đủ";
        }
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(userCartInfo.getCartId(), product.getId());
        if (Objects.isNull(cartItem)) {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setCartId(userCartInfo.getCartId());
            cartItem.setProductId(productId);
        } else {
            int oldQuantity = cartItem.getQuantity();
            int sum = Math.addExact(oldQuantity, quantity);
            if (maxQuantity < sum) {
                return "Số lượng sản phẩm không đủ";
            }
            cartItem.setQuantity(sum);
        }
        cartItemRepository.saveAndFlush(cartItem);
            return "Đã thêm vào giỏ hàng!";
        } catch (Exception ex) {
            return "Có lỗi xảy ra!";
        }
    }

    @Override
    public CartDTO viewCart() {
        List<CartItemDTO> cartItemDTOList = cartItemRepository.findAllByCartId(userCartInfo.getCartId());
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartItemDTOList(cartItemDTOList);
        cartDTO.setCartId(userCartInfo.getCartId());
        return cartDTO;
    }

    @Transactional
    @Override
    public void removeProductToCart(UUID productId) {
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(userCartInfo.getCartId(), productId);
        if (Objects.nonNull(cartItem)) {
            cartItemRepository.delete(cartItem);
        }
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        } else {
            return "No authenticated user";
        }
    }


}
