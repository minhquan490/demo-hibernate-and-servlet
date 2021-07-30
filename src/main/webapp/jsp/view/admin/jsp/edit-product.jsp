<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Edit Product</title>
        </head>

        <body>
            <div>
                <form action="${pageContext.request.contextPath}/admin/product/edit" method="post" enctype="multipart/form-data">
                    ID:&nbsp;
                    <input type="text" name="idProduct" value="${product.id}" readonly><br> Product Code:&nbsp;
                    <input type="text" name="code" value="${product.code}" readonly><br>Name:&nbsp;
                    <input type="text" name="productName" value="${product.name}" readonly><br> Price:&nbsp;
                    <input type="text" name="price" value="${product.price}"><br>Category:&nbsp;
                    <input type="text" name="categoryName" value="${product.category.name}" readonly>
                    <br>Picture:&nbsp;
                    <input type="file" name="image" value="${product.picture}"><br>
                    <button type="submit">Edit Product</button>
                </form>
                <a href="${pageContext.request.contextPath}/admin/product/list" type="button">View list product</a>
            </div>
        </body>

        </html>