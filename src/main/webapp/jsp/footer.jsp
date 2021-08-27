<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="footer ">
            <div class="grid ">
                <div class="gird__row " style="display: inline-flex;width: 1200px; ">
                    <div class="grid__column-2-4 " style="width: 20%; ">
                        <h3 class="footer__heading ">Chăm sóc khách hàng</h3>
                        <ul class="footer__list ">
                            <li class="footer__list-item ">
                                <!-- TODO create page to help user, bot to chat with user and mini chat app to contact user-->
                                <a href=" " class="footer__list-link ">Trung Tâm Trợ Giúp</a>
                            </li>
                            <li class="footer__list-item ">
                                <a href=" " class="footer__list-link ">Demo Mail</a>
                            </li>
                            <li class="footer__list-item ">
                                <!-- TODO create page user guide to buy product -->
                                <a href=" " class="footer__list-link ">Hướng Dẫn Mua Hàng</a>
                            </li>
                        </ul>
                    </div>
                    <div class="grid__column-2-4 " style="width: 20%; ">
                        <h3 class="footer__heading ">Giới thiệu</h3>
                        <ul class="footer__list ">
                            <li class="footer__list-item ">
                                <!-- TODO create page to introduce yourself -->
                                <a href=" " class="footer__list-link ">Về Chúng Tôi</a>
                            </li>
                            <li class="footer__list-item ">
                                <!-- TODO create page to recruitment -->
                                <a href=" " class="footer__list-link ">Tuyển Dụng</a>
                            </li>
                            <li class="footer__list-item ">
                                <!-- TODO create page term  -->
                                <a href=" " class="footer__list-link ">Điều Khoản</a>
                            </li>
                        </ul>
                    </div>
                    <div class="grid__column-2-4 " style="width: 20%; ">
                        <h3 class="footer__heading ">Danh mục</h3>
                        <ul class="footer__list ">
                            <c:forEach items="${listCategories} " var="category ">
                                <li class="footer__list-item ">
                                    <a href="${pageContext.request.contextPath}/search?select=0&nameCategory=${category.name} " class="footer__list-link ">${category.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="grid__column-2-4 " style="width: 20%; ">
                        <h3 class="footer__heading ">Theo dõi</h3>
                        <ul class="footer__list ">
                            <li class="footer__list-item ">
                                <a href="https://www.facebook.com/profile.php?id=100009585947523 " class="footer__list-link ">
                                    <i class="footer__list-icon fab fa-facebook "></i>Facebook
                                </a>
                            </li>
                            <li class="footer__list-item ">
                                <a href="https://github.com/minhquan490 " class="footer__list-link ">
                                    <i class="footer__list-icon fab fa-github "></i>Git Hub
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="grid__column-2-4 " style="width: 20%; ">
                        <h3 class="footer__heading ">Thanh toán</h3>
                        <ul class="footer__list ">
                            <li class="footer__list-item ">
                                <a href="# " class="footer__list-link ">
                                    <i class="footer__list-icon fab fa-cc-visa "></i>Visa
                                </a>
                            </li>
                            <li class="footer__list-item ">
                                <a href="# " class="footer__list-link ">
                                    <i class="footer__list-icon fab fa-cc-mastercard "></i>Master Card
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>