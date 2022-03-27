function searchAjax() {
    $.ajax({
        dataType : "json",
        url : 'home',
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        type: 'POST',
        data:'{"selectedColumn":"' + $('#selectColumn').find('select').val() + '"}',

        success : function(response) {
            $('#unique').find('tr').remove();
            var headerRow ="<tr><th> id </th><th>kolumna1 </th><th>kolumna2 </th><th>kolumna3 </th><th>kolumna4 </th></tr>";
            $('#unique').append(headerRow);
            $.each( response.uniqueValues,function(index, row) {
                var htmlrow ="<tr><td>" + row.id + "</td><td>" + row.kolumna1 + "</td><td>" + row.kolumna2 + "</td><td>" + row.kolumna3 + "</td><td>" + row.kolumna4 + "</td></tr>";
                $('#unique').append(htmlrow);
            });

            $('#duplicated').find('tr').remove();
            var headerRow ="<tr><th> id </th><th>kolumna1 </th><th>kolumna2 </th><th>kolumna3 </th><th>kolumna4 </th></tr>";
            $('#duplicated').append(headerRow);
            $.each( response.duplicatedValues,function(index, row) {
                var htmlrow ="<tr><td>" + row.id + "</td><td>" + row.kolumna1 + "</td><td>" + row.kolumna2 + "</td><td>" + row.kolumna3 + "</td><td>" + row.kolumna4 + "</td></tr>";
                $('#duplicated').append(htmlrow);
            });

        },
        error : function(){
            alert("error");
        }
    });
}