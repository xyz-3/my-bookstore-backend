package com.example.bookstore.dao.impl;

import com.alibaba.fastjson.JSONArray;
import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.CartItem;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.OrderItemRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.util.Redis.RedisUtil;
import com.example.bookstore.util.request.BookStorageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Book findBookById(Long id) {
        Book book;
        Object bookCache = redisUtil.get("book_" + id);
        if(bookCache != null){
            book = JSONArray.parseObject(bookCache.toString(), Book.class);
            System.out.println("get book from redis");
        }else{
            book = bookRepository.findById(id).get();
            redisUtil.set("book_" + id, JSONArray.toJSONString(book));
            System.out.println("get book from database");
        }
        return book;
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Boolean setBookInfo(Long id, BookStorageForm bookStorageForm) {
        Object bookCache = redisUtil.get("book_" + id);
        if(bookCache != null){
            redisUtil.del("book_" + id);
        }


        Book book = bookRepository.getById(id);
        book.setTitle(bookStorageForm.getTitle());
        book.setAuthor(bookStorageForm.getAuthor());
        book.setPrice(bookStorageForm.getPrice());
        book.setImage(bookStorageForm.getImage());
        book.setPublisher(bookStorageForm.getPublisher());
        book.setStock(bookStorageForm.getStock());
        book.setIntroduction(bookStorageForm.getIntroduction());
        book.setIsbn(bookStorageForm.getIsbn());
        bookRepository.save(book);
        redisUtil.set("book_" + id, JSONArray.toJSONString(book));
        return true;
    }


    @Override
    public List<Book> deleteById(Long id) {
        Object bookCache = redisUtil.get("book_" + id);
        if(bookCache != null){
            redisUtil.del("book_" + id);
        }


        Book book = bookRepository.getById(id);

        if(book == null) return null;

        List<CartItem> cartItems = book.getCartItemSet();
        book.getCartItemSet().clear();
        cartRepository.deleteAll(cartItems);

        //delete relevant orderItems and update Orders
        List<OrderItem> orderItems = book.getOrderItemSet();
        for(OrderItem orderItem : orderItems){
            Order order = orderItem.getOrder();
            order.getOrderItems().remove(orderItem);
            orderRepository.save(order);
        }
        bookRepository.delete(book);
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Long addBook(BookStorageForm bookStorageForm){
        Object bookCache = redisUtil.get("book_" + bookStorageForm.getIsbn());
        if(bookCache != null){
            redisUtil.del("book_" + bookStorageForm.getId());
        }

        Book book = new Book();
        book.setTitle(bookStorageForm.getTitle());
        book.setAuthor(bookStorageForm.getAuthor());
        book.setPrice(bookStorageForm.getPrice());
        book.setIntroduction(bookStorageForm.getIntroduction());
        book.setPublisher(bookStorageForm.getPublisher());
        book.setStock(bookStorageForm.getStock());
        book.setIsbn(bookStorageForm.getIsbn());
        if(bookStorageForm.getImage() == null){
            book.setImage("https://th.bing.com/th/id/R.320c59f40934c54d19db1be80808845b?rik=SnMKCkjl%2bm7gzw&riu=http%3a%2f%2fwww.newdesignfile.com%2fpostpic%2f2009%2f11%2fblank-book-cover-template_309782.jpg&ehk=LICeLH8KXMSXYdtEl2nxWFh%2bp5fb%2fOL1IF5SKhm5bwE%3d&risl=&pid=ImgRaw&r=0");
        }else{
            book.setImage(bookStorageForm.getImage());
        }
        bookRepository.save(book);
        redisUtil.set("book_" + book.getId(), JSONArray.toJSONString(book));
        return book.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public Book updateStock(Long bookId, Integer quantity){
        Object bookCache = redisUtil.get("book_" + bookId);
        if(bookCache != null){
            redisUtil.del("book_" + bookId);
        }

        Book book = bookRepository.getById(bookId);
        if(book == null) return null;
        if(book.getStock() < quantity) return null;
        book.setStock(book.getStock() - quantity);
        bookRepository.save(book);
        redisUtil.set("book_" + bookId, JSONArray.toJSONString(book));
        return book;
    }
}
