function calculateTotal()

  let priceBill={}

  var difference = $("#checkOut").getTime() - $("#checkIn").getTime();
  difference = difference + 1;

  priceBill = ($("#price").val() * difference );
  $("#bill").text(priceBill);


}
$(function()
   {
      $(".field-validation-error").on("change keyup",calculateTotal)
  })