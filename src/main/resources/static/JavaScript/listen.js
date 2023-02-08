document.addEventListener("DOMContentLoaded", function () {
    const updateSectionButtons = document.getElementsByClassName("updateSectionButton");
    const updateSectionButtonsArray = Array.from(updateSectionButtons);

    const updateSections = document.getElementsByClassName("updateSection");
    const updateSectionsArray = Array.from(updateSections);

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

const currentTrack = 0;
const currentTrackName = "";
const playIconContainer = document.getElementById('play-icon');
const state = 'play';
const playPauseBoolean = true;
let testString = "TESTSTRING";

function skipButton() {
    const audio = document.getElementById("player");
    audio.load();
    audio.play();
    this.currentTrack += 1;
    console.log(this.currentTrack);
  }

function previousButton() {
    const audio = document.getElementById("player");
    audio.play();
    this.currentTrack -= 1;
    console.log(this.currentTrack);
  }

function pause() {
    const audio = document.getElementById("player");
    audio.pause();
  }

function play() {
    const audio = document.getElementById("player");
    audio.play();
  }

function skipBooleanSwitch (){
    this.playPauseBoolean = false;
  }

function playPauseBooleanSwitch () {
    if (this.playPauseBoolean === true) {
    this.playPauseBoolean = false;
    } else {
      this.playPauseBoolean = true;
    }
  }

function playPauseBooleanForceTrue (){
    this.playPauseBoolean = true;
  }

function playPauseBooleanForceFalse (){
    this.playPauseBoolean = false;
  }

function stopButton() {
    const myPlayer = document.getElementsByTagName('audio')[0];
    const audio = document.getElementById("player");
    audio.pause();
    myPlayer.currentTime = 0;
    let songTitle = document.getElementById("songTitle");
  }


 function onShow(event) {
      const audio = document.getElementById("currentSong");
      let x = event.target.innerHTML;
      for (let i=0; i<this.songs.length; i++){
          if (x.includes(this.songs[i].title)){
            const audio = document.getElementById("player");
            audio.load();
            audio.play();
            this.currentTrack = i;
          }
      }
    }