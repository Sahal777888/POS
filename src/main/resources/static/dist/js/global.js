//validasi error
    $('#modal-form').on('submit','#form-input', function (){
      $.ajax({
        url: $(this).attr('action'),
        data: $(this).serialize(),
        dataType: 'html',
        type: 'POST',
        success: function (data){
          // masukan dulu result ke modal
          $('#modal-form').find('.modal-content').html(data);
          // check jumlah element yang memiliki class has-error
          var check = $("#modal-form").find(".modal-content").find(".error").length;
          console.log("count invalid : "+ check);
          // jika tidak ada error
          if(check==0){
            $("#modal-form").modal('hide');
            location.reload();
          }
        }
      });
      return false;
    });

    //program lama
    $("#pop-add").on("click", function (e){
      e.preventDefault();
      var url = $(this).attr('href');
      $.ajax({
        url:url,
        dataType: 'HTML',
        method: 'GET',
        success: function (result){
          $('#modal-form').find('.modal-content').html(result);
          //$('#modal-form').find('.card-title').html("New Fakultas");
          $("#modal-form").modal('show');
        }
      })
    })
    $(".pop-detail").on("click", function (e){
      e.preventDefault();
      var url = $(this).attr('href');
      $.ajax({
        url:url,
        dataType: 'HTML',
        method: 'GET',
        success: function (result){
          $('#modal-form').find('.modal-content').html(result);
          //$('#modal-form').find('.card-title').html("Detail Fakultas");
          $("#modal-form").modal('show');
        }
      })
    })
    $(".pop-edit").on("click", function (e){
      e.preventDefault();
      var url = $(this).attr('href');
      $.ajax({
        url:url,
        dataType: 'HTML',
        method: 'GET',
        success: function (result){
          $('#modal-form').find('.modal-content').html(result);
          //$('#modal-form').find('.card-title').html("Edit Fakultas");
          $("#modal-form").modal('show');
        }
      })
    })


/*
    $("#pop-add").on("click", function (e){
      e.preventDefault();
      var url = $(this).attr('href');
      $.ajax({
        url:url,
        dataType: 'HTML',
        method: 'GET',
        success: function (result){
          $('#modal-form').find('.modal-content').html(result);
          $("#modal-form").modal('show');
        }
      })
    })
    $(".pop-detail").on("click", function (e){
      e.preventDefault();
      var url = $(this).attr('href');
      $.ajax({
        url:url,
        dataType: 'HTML',
        method: 'GET',
        success: function (result){
          $('#modal-form').find('.modal-content').html(result);
          $("#modal-form").modal('show');
        }
      })
    })
    $(".pop-edit").on("click", function (e){
      e.preventDefault();
      var url = $(this).attr('href');
      $.ajax({
        url:url,
        dataType: 'HTML',
        method: 'GET',
        success: function (result){
          $('#modal-form').find('.modal-content').html(result);
          $("#modal-form").modal('show');
        }
      })
    })
*/