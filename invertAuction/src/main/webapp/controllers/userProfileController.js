invertAuctionApp.controller('userProfileController', function($scope, $http,$window,$location,LoginFactory) {

    $http.get("/api/job-categories/all").then(function (response) {

        console.log("JOB CATEGORIES: " + JSON.stringify(response.data));
        $scope.jobCategories = response.data;

    },function () {
        console.log("Failed to retrieve job categoories");
    });
    
    $scope.startNewIA = function () {

        var index = $scope.jobCategories.indexOf($scope.job_category) + 1;
        var newProcProcesProps={
            'job_category' : index,
            'job_description' : $scope.job_description,
            'job_max_price' : $scope.job_max_price,
            'job_application_deadline' : $scope.job_application_deadline,
            'job_min_candidates' :$scope.job_min_candidates,
            'job_deadline' : $scope.job_deadline
        }

        console.log("new proces props: " + JSON.stringify(newProcProcesProps));
        $http.post("/api/start-ia",newProcProcesProps).then(function () {
           console.log("start-ia successfull");
        }, function () {
            console.log("start-ia failed");
        });
    }

});