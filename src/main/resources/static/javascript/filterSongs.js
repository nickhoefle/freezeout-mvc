document.addEventListener("DOMContentLoaded", function() {

    function filterTable() {
        let input, filter, table, tr, td, i, txtValueSong, txtValueArtist;
        input = document.getElementById("filterInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            tdSong = tr[i].getElementsByTagName("td")[1];
            tdArtist = tr[i].getElementsByTagName("td")[2];
            if (tdSong && tdArtist) {
                txtValueSong = tdSong.textContent || tdSong.innerText;
                txtValueArtist = tdArtist.textContent || tdArtist.innerText;
                if (
                    txtValueSong.toUpperCase().indexOf(filter) > -1 ||
                    txtValueArtist.toUpperCase().indexOf(filter) > -1
                ) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }

    document.getElementById("filterInput").addEventListener("input", filterTable);

});