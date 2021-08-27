<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="vi">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">

            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
            <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap">
            <link rel="stylesheet" href="./jsp/view/assets/css/index.css">
            <link rel="stylesheet" href="./jsp/view/assets/css/base.css">
            <link rel="stylesheet" href="./jsp/view/assets/css/reponsive.css">
            <link rel="stylesheet" href="./jsp/view/assets/fonts/fontawesome/css/all.min.css">

            <title>Demo Hibernate and Servlet</title>
        </head>

        <body>
            <div class="main">
                <jsp:include page="/jsp/header.jsp" />
                <div class="container">
                    <div class="grid">
                        <div class="grid__row container__content">
                            <div class="grid__column-2">
                                <nav class="category">
                                    <h3 class="category__heading">
                                        <i class="category__heading-icon fas fa-list"></i>Danh mục
                                    </h3>
                                    <ul class="category-list">
                                        <c:forEach items="${listCategories}" var="category">
                                            <li class="category-item">
                                                <a href="${pageContext.request.contextPath}/search?select=0&nameCategory=${category.name}" class="category-item--active category-item__link">${category.name}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </nav>
                            </div>
                            <div class="grid__column-10">
                                <div class="home-filter">
                                    <span class="home-filter__label">Sắp xếp theo</span>
                                    <div class="select-input">
                                        <span class="select-input__label">Giá</span>
                                        <i class="select-input__icon fas fa-angle-down"></i>
                                        <ul class="select-input__list">
                                            <!-- TODO using AJAX to sort -->
                                            <li class="select-input__item">
                                                <a href="" class="select-input__link">Giá: Thấp đến cao</a>
                                            </li>
                                            <li class="select-input__item">
                                                <a href="" class="select-input__link">Giá: Cao đến thấp</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="home-filter__page">
                                        <span class="home-filter__page-num">
                                            <!-- TODO using AJAX -->
                                            <span class="home-filter__page-current">1</span>/14
                                        </span>
                                        <div class="home-filter__page-control">
                                            <!-- TODO previous page link -->
                                            <!-- TODO using AJAX -->
                                            <a href="" class="home-filter__page-btn home-filter__page-btn--disabled">
                                                <i class="home-filter__page-icon fas fa-angle-left"></i>
                                            </a>
                                            <!-- TODO next page link -->
                                            <!-- TODO using AJAX -->
                                            <a href="" class="home-filter__page-btn">
                                                <i class="home-filter__page-icon fas fa-angle-right"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="home-product">
                                    <div class="grid__row">
                                        <c:forEach items="${listProducts}" var="product">
                                            <div class="gird__column-2-4">
                                                <a href="" class="link home-product-item-wrapper">
                                                    <div class="home-product-item">
                                                        <div class="home-product-item__img" style="background-image: url(https://baoquocte.vn/stores/news_dataimages/dieulinh/012020/29/15/nhung-buc-anh-dep-tuyet-voi-ve-tinh-ban.jpg);"></div>
                                                        <h4 class="home-product-item__name">${product.name}</h4>
                                                        <div class="home-product-item__price">
                                                            <c:choose>
                                                                <c:when test="${product.salePrice > 0}">
                                                                    <span class="home-product-item__price-old">${product.price}</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="home-product-item__price-current">${product.price}</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <c:if test="${product.salePrice > 0}">
                                                                <span class="home-product-item__price-current">${product.salePrice}</span>
                                                            </c:if>
                                                        </div>
                                                        <c:if test="${product.salePrice > 0}">
                                                            <div class="home-product-item__sale-off">
                                                                <fmt:formatNumber var="discount" value="${100 - (product.salePrice/product.price) * 100}%" maxFractionDigits="1" />
                                                                <span class=" home-product-item__sale-off-percent">${discount}</span>
                                                                <span class="home-product-item__sale-off-label">GIẢM</span>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </a>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <ul class="pagination home-product__pagination">
                                    <!-- TODO using AJAX -->
                                    <li class="pagination-item">
                                        <a href="" class="pagination-item__link">
                                            <i class="pagination-item__icon fas fa-angle-left"></i>
                                        </a>
                                    </li>
                                    <li class="pagination-item pagination-item--active">
                                        <a href="" class="pagination-item__link">
                                            1
                                        </a>
                                    </li>
                                    <li class="pagination-item">
                                        <a href="" class="pagination-item__link">
                                            <i class="pagination-item__icon fas fa-angle-right"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="/jsp/footer.jsp" />
            </div>
        </body>

        </html>