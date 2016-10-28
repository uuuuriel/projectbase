var APP = angular.module("material",[]);

//--> Servicos utilitarios.
function request(url){
	
	return {
		url: "privateRest/"+ url
	};
}

function filterArray(array,objSearch){
	
	var pos = -1;
	
	angular.forEach(array,function(item,index){
		if(angular.equals(item,objSearch)){
			pos = index;
		}
	});
	
	return pos;
}

APP.service("REQ",function($http,$q){
	this.send = function(url,data,method){
		
		var req = $q.defer();
		var config = new request(url);
		
		config.method = method;
		if(data){
			config.data = data;
		}
		
		$http(config).success(function(data){
			req.resolve(data.data);
		}).error(function(msg,status){
			alert(msg.message);
			
			req.reject(msg);
		});
		
		return req.promise;
	};
});

APP.service("AJAX",function(REQ){
	this.get = function(url,data){
		return REQ.send(url,data,"GET");
	};
	this.post = function(url,data){
		return REQ.send(url,data,"POST");
	};
	this.put = function(url,data){
		return REQ.send(url,data,"PUT");
	};
	this.del = function(url,data){
		return REQ.send(url,data,"DELETE");
	};
});

//--> Servicos da aplicacao.
APP.service("CATEGORY",function(AJAX){
	this.list = function(){
		return AJAX.get("category");
	}
	this.add = function(categoria){
		return AJAX.post("category",categoria);
	};
	this.edit = function(categoria){
		return AJAX.put("category",categoria);
	};
	this.remove = function(id){
		return AJAX.del("category/"+ id);
	};
});

APP.service("STOCK",function(AJAX){
	this.add = function(stock){
		return AJAX.post("stock",stock);
	};
});

APP.controller("testeCtrl",function($scope,CATEGORY,STOCK){
	
	$scope.category = {id: 0};
	
	$scope.add = function(){
		
		if(($scope.category.id != null)&&($scope.category.id > 0)){
			CATEGORY.edit($scope.category).then(function(){
				var pos = filterArray($scope.listCategory,$scope.category);
				if(pos > -1){
					$scope.listCategory.splice(pos,1,$scope.category)
				}
				
				$scope.category = null;
			});
		}else{
			CATEGORY.add($scope.category).then(function(cat){
				$scope.category = null;
				
				$scope.listCategory.push(cat);
			});
		}
	};
	
	$scope.edit = function(list){
		$scope.category = angular.copy(list);
	};
	
	$scope.remove = function(id){
		
		if(confirm("Deseja remover essa categoria?")){
			CATEGORY.remove(id).then(function(){
				var pos = filterArray($scope.listCategory,$scope.category);
				
				if(pos > -1){
					$scope.listCategory.splice(pos,1);
				}
			});
		}
	};
	
	$scope.list = function(){
		
		CATEGORY.list().then(function(data){
			$scope.listCategory = data;
		});
	};
	
	$scope.addStock = function(stock){
		STOCK.add(stock).then(function(){
			$scope.stock = null;
		});
	};
	
	$scope.list();
});