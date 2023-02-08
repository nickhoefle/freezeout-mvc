  document.getElementById("selectedSong").addEventListener("change", function() {
    document.getElementById("playBtn").href = "/uploads/" + this.value;
  });
  document.getElementById("playBtn").addEventListener("click", function() {
    window.open(this.href, "_blank");
  });

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

function tablePlayButton () {
    const audio = <HTMLAudioElement> document.getElementById("currentSong");
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