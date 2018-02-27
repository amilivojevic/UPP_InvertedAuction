/**
 * Created by Sandra on 7/13/2017.
 */
(function(angular) {
    angular.module('invertAuctionApp')
        .factory('LoginFactory', function($http) {

            return {
                getLoggedUserData: function(token) {

                    return $http.get('/api/users/data')
                        .then(function(loggedUserData) {
                            console.log("fabrika; logged user: " + JSON.stringify(loggedUserData));
                            return loggedUserData.data;
                        });
                }

            }

        });
})(angular);
