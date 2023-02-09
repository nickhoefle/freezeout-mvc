document.addEventListener("DOMContentLoaded", function () {
    const updateSections = document.getElementsByClassName("updateSection");
    const updateSectionsArray = Array.from(updateSections);

    const updateSectionButtons = document.getElementsByClassName("updateSectionButton");
    const updateSectionButtonsArray = Array.from(updateSectionButtons);

    updateSectionButtonsArray.forEach(function (button, index) {
        button.addEventListener("click", function () {
            if (updateSectionButtonsArray[index].innerHTML === "❌ Cancel") {
                updateSectionsArray[index].style.display = 'none';
                updateSectionButtonsArray[index].innerHTML = "Update"
                return;
            }
            updateSectionsArray[index].style.display = ""
            updateSectionButtonsArray[index].innerHTML = "❌ Cancel"
        });
    });
});

function openSelectedInNewTab() {
    const playButtons = document.getElementsByClassName("playBtn");
    const playButtonsArray = Array.from(playButtons);

    const selectedSong = document.getElementsByClassName("selectedSong");
    const selectedSongArray = Array.from(selectedSong);

    playButtonsArray.forEach(function(button, index) {
        button.addEventListener("click", function() {
            if (selectedSongArray[index].value === 'images' || selectedSongArray[index].value === 'Hidden') {
                return;
            }
            window.open('/uploads/' + selectedSongArray[index].value)
        });
    });
}

function toggleUpdateSection() {
    updateSectionButtonsArray.forEach(function(button, index) {
        button.addEventListener("click", function() {
            if (updateSectionButtonsArray[index].innerHTML === "❌ Cancel") {
                updateSectionsArray[index].style.display = 'none';
                updateSectionButtonsArray[index].innerHTML = "Update"
                return;
            }
            updateSectionsArray[index].style.display = ""
            updateSectionButtonsArray[index].innerHTML = "❌ Cancel"
        });
    });
}
