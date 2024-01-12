document.addEventListener("DOMContentLoaded", function() {
    updateRowIndexes();
});

function updateRowIndexes() {
    var rows = document.querySelectorAll('tbody tr');

    rows.forEach(function(row, index) {
        var input = row.querySelector('.photoOrderInput');
        if (input) {
            input.value = index + 1; // Set 1-based index
        }
    });
}

function moveRowUp(event, button) {
    event.preventDefault();

    var row = button.closest('tr');
    var previousRow = row.previousElementSibling;

    if (previousRow) {
        row.parentNode.insertBefore(row, previousRow);
        updateRowIndexes();
    }
}

function moveRowDown(event, button) {
    event.preventDefault();

    var row = button.closest('tr');
    var nextRow = row.nextElementSibling;

    if (nextRow) {
        row.parentNode.insertBefore(nextRow, row);
        updateRowIndexes();
    }
}