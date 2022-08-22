function logout() {
$(document).ready(function(){
    $.get('/logout',function(data,status){
        $("#mainContainer").html(data);
    });
  });
}

function filterTasks(start,end,status){
    let startDate = start.val();
    let endDate = end.val();
    $(document).ready(function(){
        $.get('/api/v1/tasks/employee/filter',{startDate,endDate,status},function(data,status){
            $("#mainContainer").html(data);
        });
      });
}

function createTask(){
    $(document).ready(function(){
        $.get('/api/v1/tasks/employee/create',function(data,status){
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