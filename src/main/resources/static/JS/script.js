//REGION videos

let player, isPlaying = false, apiReady = false;

// Cargar API de YouTube
(function loadYT(){
const tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
document.head.appendChild(tag);
})();

// La API llamará esta función
function onYouTubeIframeAPIReady() {
player = new YT.Player('ytplayer', {
    height: '0',
    width: '0',
    playerVars: { autoplay: 0, controls: 0 },
    events: { onReady: () => { apiReady = true; } }
});
}

// Parseo robusto de links de YouTube
function parseYouTube(urlStr) {
try {
    const u = new URL(urlStr);
    const host = u.hostname.replace('www.', '');
    const listId = u.searchParams.get('list');

    if (host === 'youtu.be') {
    const videoId = u.pathname.slice(1); // /VIDEOID
    return { videoId, listId };
    }
    if (host === 'youtube.com' || host === 'm.youtube.com' || host === 'music.youtube.com') {
    if (u.pathname === '/watch') {
        return { videoId: u.searchParams.get('v'), listId };
    }
    if (u.pathname.startsWith('/shorts/')) {
        return { videoId: u.pathname.split('/')[2], listId };
    }
    if (u.pathname === '/playlist' && listId) {
        return { videoId: null, listId };
    }
    }
} catch {}
return { videoId: null, listId: null };
}

// Botón ON/OFF
document.getElementById("toggleMusic").addEventListener("click", () => {
if (!apiReady || !player?.playVideo) return;
const icon = document.getElementById("icon");
if (isPlaying) {
    player.pauseVideo();
    icon.className = "bi bi-volume-up";
} else {
    player.playVideo();
    icon.className = "bi bi-volume-mute";
}
isPlaying = !isPlaying;
});

// Cambio de música
document.getElementById("musicSelect").addEventListener("change", (e) => {
const link = e.target.value;
if (!link || !apiReady) return;

const { videoId, listId } = parseYouTube(link);

if (listId && !videoId) {
    player.loadPlaylist({ list: listId, listType: 'playlist' });
} else if (videoId) {
    player.loadVideoById(videoId);
} else {
    alert("Link de YouTube no reconocido.");
    return;
}

// Reiniciar a estado pausado
player.pauseVideo();
isPlaying = false;
document.getElementById("icon").className = "bi bi-volume-up";
});
//FIN de region videos
