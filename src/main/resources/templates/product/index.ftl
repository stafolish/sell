<html>
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
        <#include "../common/nav.ftl">
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" method="post" action="/sell/seller/product/save">
                            <div class="form-group">
                                <label>名称</label>
                                <input name="productName" class="form-control" type="text" value="${(productInfo.productName)!''}" />
                            </div>
                            <div class="form-group">
                                <label>价格</label>
                                <input name="productPrice" class="form-control" type="text" value="${(productInfo.productPrice)!''}" />
                            </div>
                            <div class="form-group">
                                <label>库存</label>
                                <input name="productStock" class="form-control" type="number" value="${(productInfo.productStock)!''}" />
                            </div>
                            <div class="form-group">
                                <label>描述</label>
                                <input name="productDescription" class="form-control" type="text" value="${(productInfo.productDescription)!''}" />
                            </div>
                            <div class="form-group">
                                <label>图片</label>
                                <img height="500" width="700" src="${(productInfo.productIcon)!''}">
                                <input name="productIcon" class="form-control" type="text" value="${(productInfo.productIcon)!''}" />
                            </div>
                            <div class="form-group">
                                <label>类目</label>
                                <select name="categoryType" class="form-control">
                                    <#list categoryList as category>
                                        <#if (productInfo.categoryType)?? && category.categoryType=productInfo.categoryType>
                                            <option value="${category.categoryType}" selected="selected">${category.categoryName}</option>
                                        <#else>
                                            <option value="${category.categoryType}">${category.categoryName}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </div>
                            <input hidden name="productId" value="${(productInfo.productId)!''}">
                            <button type="submit" class="btn btn-default">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

</div>
</body>

</html>
