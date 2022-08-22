function logout() {
$(document).ready(function(){
    $.get('/logout',function(data,status){
        $("#mainContainer").html(data);
    });
  });
}

function filterTasks(email,status){
    $(document).ready(function(){
        $.get('/api/v1/tasks/employee/filter',{email,status},function(data,status){
            $("#mainContainer").html(data);
        });
      });
}

function updateTask(row){
    let id = row.find("td:eq(5)").text();
    $(document).ready(function(){
        $.get('/api/v1/tasks/employee/update/status/' + id,function(data,status){
            $("#mainContainer").html(data);
        });
      });
}