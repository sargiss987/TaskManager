function logout() {
$(document).ready(function(){
    $.get('/logout',function(data,status){
        $("#mainContainer").html(data);
    });
  });
}

function createTask(){
    $(document).ready(function(){
        $.get('/api/v1/tasks/manager/create',function(data,status){
            $("#mainContainer").html(data);
        });
      });
}

function updateTask(row){
    let id = row.find("td:eq(5)").text();
    $(document).ready(function(){
        $.get('/api/v1/tasks/manager/update/' + id,function(data,status){
            $("#mainContainer").html(data);
        });
      });
}

function filterTasks(email,status){
    $(document).ready(function(){
        $.get('/api/v1/tasks/manager/filter',{email,status},function(data,status){
            $("#mainContainer").html(data);
        });
      });
}

function deleteTask(row) {
    let id = row.find("td:eq(5)").text();
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: '/api/v1/tasks/manager/delete/' + id,
        type: 'DELETE',
        beforeSend: function(xhr) {
           xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function(data){
            return $('#mainContainer').html(data);
        }
    });
}