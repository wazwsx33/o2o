/**
 *
 */
$(function () {
    var initUrl = '/shop/getshopinitinfo';
    var registerShopUrl = '/shop/registershop';
    getShopInitInfo();

    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (value, index) {
                    tempHtml += '<option data-id="' + value.shopCategoryId + '">'
                     + value.shopCategoryName + '</option>';
                });
                data.areaList.map(function (value, index) {
                    tempAreaHtml += '<option data-id="' + value.areaId + '">'
                        + value.areaName + '</option>';
                });
                $('#shop-category').html(tempHtml);
                $('#area-category').html(tempAreaHtml);
            }
        });

        $('#submit').click(function () {
            var shop = {};
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId : $('#shop-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId : $('#area-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            var shopImg = $('#shop-img')[0].files[0];
            var formData = new FormData();
            formData.append('shopImg', shopImg);
            formData.append('shopStr', JSON.stringify(shop));
            var verifyCodeActual = $('#j_kaptcha').val();
            if (!verifyCodeActual) {
                $.toast("请输入验证码！");
                return;
            }
            formData.append('verifyCodeActual', verifyCodeActual);

            $.ajax({
               url : registerShopUrl,
               type : 'POST',
               data : formData,
               contentType : false,
               processData : false,
               cache : false,
               success : function (data) {
                   if (data.success) {
                       $.toast('提交成功！');
                   } else {
                       $.toast('提交失败！' + data.errMsg);
                   }
                   $('#j_kaptcha').click();
               }
            });
        });
    }
})