$(document).ready( function () {  
    $('#example').DataTable({searching: false, lengthChange: false, pageLength: 10, "columns": [
    null,
    null,
    null,
    null,
    null,
    null,
    { width: "35%" }
  ]});  
} )

function myFunction() {
  if(document.getElementById("searchText").value.length == 0){ url = "/restGetEmployees"; }
  else{ url = "/restSearchEmployee/"+document.getElementById("searchText").value; }
  	$.ajax({
        type: "GET",
        url: url,
        success: function(response){
        	document.getElementById("employeeRows").innerHTML = '';
        	for(var i=0;i<response.length;i++){
        		var ans = '';
        		ans += '<tr>' ;
        		ans += '<td>'+ response[i].empId +'</td>' ;
        		ans += '<td>'+ response[i].empName +'</td>' ;
        		ans += '<td>'+ response[i].empGender +'</td>' ;
        		ans += '<td>'+ response[i].empEmail +'</td>' ;
        		ans += '<td>'+ response[i].empPhone +'</td>' ;
        		ans += '<td>'+ response[i].empPosition +'</td>' ;
        		ans += '<td> <a href="viewEmployee/'+ response[i].empId +'">View</a> </td>' ;
        		ans += '<td> <a href="addAddress/'+ response[i].empId +'">Add Address</a> </td>' ;
        		ans += '<td> <a href="editEmployee/'+ response[i].empId +'">Edit</a> </td>' ;
        		ans += '<td> <a href="deleteEmployee/'+ response[i].empId +'">Delete</a> </td>' ;
        		ans += '</tr>' ;
        		document.getElementById("employeeRows").innerHTML += ans;
        	}
        },
        error: function(e){
        	alert('Error: ' + e);
        }
	});
}