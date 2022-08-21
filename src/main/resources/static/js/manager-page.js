function logout() {
$(document).ready(function(){
    $.get('/logout',function(data,status){
        $("#mainContainer").html(data);
    });
  });
}

function createTask(){
    $(document).ready(function(){
        $.get('/api/v1/tasks/create',function(data,status){
            $("#mainContainer").html(data);
        });
      });
}

function updateTask(id){
    $(document).ready(function(){
        $.get('/api/v1/tasks/update/' + id,function(data,status){
            $("#mainContainer").html(data);
        });
      });
}

function filterTasks(email,status){
    $(document).ready(function(){
        $.get('/api/v1/tasks/filter',{email,status},function(data,status){
            $("#mainContainer").html(data);
        });
      });
}

function deleteTask(id) {
    console.log(id);
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: '/api/v1/tasks/delete/' + id,
        type: 'DELETE',
        beforeSend: function(xhr) {
           xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function(data){
            return $('#mainContainer').html(data);
        }
    });
}