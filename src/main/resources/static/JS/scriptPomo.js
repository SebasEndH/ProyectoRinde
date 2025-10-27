let workTime = 25 * 60;
let breakTime = 5 * 60;
let remainingTime = workTime;
let isWorkSession = true;
let timerInterval = null;

const timerDisplay = document.getElementById("timer");
const statusDisplay = document.getElementById("status");
const startBtn = document.getElementById("startBtn");
const pauseBtn = document.getElementById("pauseBtn");
const resetBtn = document.getElementById("resetBtn");


function updateDisplay() {
  let minutes = Math.floor(remainingTime / 60);
  let seconds = remainingTime % 60;
  timerDisplay.textContent = 
    `${minutes.toString().padStart(2, "0")}:${seconds.toString().padStart(2, "0")}`;
}

function startTimer() {
  if (!timerInterval) {
    timerInterval = setInterval(() => {
      remainingTime--;
      updateDisplay();

      if (remainingTime <= 0) {
        clearInterval(timerInterval);
        timerInterval = null;
        isWorkSession = !isWorkSession;
        remainingTime = isWorkSession ? workTime : breakTime;
        statusDisplay.textContent = isWorkSession ? "Sesión de trabajo" : "Descanso";
        startTimer();
      }
    }, 1000);
  }
}

function pauseTimer() {
  clearInterval(timerInterval);
  timerInterval = null;
}

function resetTimer() {
  clearInterval(timerInterval);
  timerInterval = null;
  isWorkSession = true;
  remainingTime = workTime;
  statusDisplay.textContent = "Sesión de trabajo";
  updateDisplay();
}

// Eventos
startBtn.addEventListener("click", startTimer);
pauseBtn.addEventListener("click", pauseTimer);
resetBtn.addEventListener("click", resetTimer);

// Inicializar
updateDisplay();