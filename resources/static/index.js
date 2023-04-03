angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:6868/market/api/v1/';
    $scope.pageIndex = 1;
    $scope.token = null;

    $scope.loadProducts = function () {
        $http.get('http://localhost:6868/market/api/v1/products').then(function (response) {
            $scope.ProductList = response.data;
        })
    };

    $scope.showProductInfo = function (ProductId) {
        $http.get('http://localhost:6868/market/api/v1/products/' + ProductId).then(function (response) {
            alert('Наименование: ' + response.data.title + ' Цена за шт:  ' + response.data.price);
            console.log(response.data);
        })

    };
    $scope.deleteProduct = function (ProductId) {
        $http.delete('http://localhost:6868/market/api/v1/products/delete/' + ProductId).then(function () {
            $scope.loadProducts();
        })

    };
    $scope.deleteProductInCart = function (ProductId) {
        $http.get('http://localhost:6868/market/api/v1/cart/delete/' + ProductId).then(function (response) {
            $scope.cart = response.data
            console.log($scope.cart)
        })
    };
    $scope.clearCart = function () {
        $http.get('http://localhost:6868/market/api/v1/cart/clear').then(function (response) {
            $scope.cart = response.data
        })
    };
    $scope.loadCart = function () {
        $http.get('http://localhost:6868/market/api/v1/cart').then(function (response) {
            $scope.cart = response.data
            console.log($scope.cart)
        })
    };
    $scope.addToCart = function (productId) {
        $http.get('http://localhost:6868/market/api/v1/cart/add/' + productId).then(function (response) {
            $scope.cart = response.data
            // $scope.loadCart();
        })
    };
    $scope.changePcs = function (title, del) {
        $http({
            url: 'http://localhost:6868/market/api/v1/cart/change',
            method: 'GET',
            params: {
                productTitle: title,
                delta: del
            }
        }).then(function (response) {
            $scope.cart = response.data
            // $scope.loadCart();
        })
    };

    $scope.isUserLoggedIn = function () {
        if ($scope.token == null){
            return false;
        }else return true;
    };
    $scope.tryToAuth = function () {
        $http({
            url: 'http://localhost:6868/market/auth',
            method: 'POST',
            data: $scope.user,
        })
            .then(function (request) {
                //
                // $scope.userinfo = request.data;
                // console.log(request.data)
                $scope.token = request.data.token;
            })
    };
    $scope.loadProducts();
    $scope.loadCart();
});