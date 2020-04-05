app.factory('AuthInterceptor', [function () {
    return {
        'request': function (config) {
            config.headers = config.headers || {};
            const encodedString = btoa("admin:password");
            config.headers.Authorization = 'Basic ' + encodedString;
            return config;
        }
    };
}]);