invertAuctionApp.controller('userProfileController', function($scope, $http,$window,$location,LoginFactory) {

    $http.get("/api/job-categories/all").then(function (response) {

        console.log("JOB CATEGORIES: " + JSON.stringify(response.data));
        $scope.jobCategories = response.data;

    },function () {
        console.log("Failed to retrieve job categoories");
    });
    
    $scope.startNewIA = function () {
        var newProcProcesProps={
            'job_category' : $scope.job_category,
            'job_description' : $scope.job_description,
            'job_max_price' : $scope.job_max_price,
            'job_application_deadline' : $scope.job_application_deadline,
            'job_min_candidates' :$scope.job_min_candidates,
            'job_deadline' : $scope.job_deadline
        }

        console.log("new proces props: " + JSON.stringify(newProcProcesProps));
        $http.post("/api/")
    }

});