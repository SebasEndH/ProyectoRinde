document.addEventListener('DOMContentLoaded', function () {
  const select = document.getElementById('musicSelect');
  const toggleBtn = document.getElementById('toggleMusic');
  const icon = document.getElementById('icon');
  const ytContainer = document.getElementById('ytplayer');

  let iframe = null;
  let playing = true; // por defecto icono muestra sonido

  function toEmbedUrl(url) {
    if (!url) return '';
    try {
      // youtu.be short
      if (url.includes('youtu.be/')) {
        const id = url.split('youtu.be/')[1].split(/[?&]/)[0];
        return 'https://www.youtube.com/embed/' + id + '?autoplay=1&mute=0';
      }
      // watch?v=
      if (url.includes('watch?v=')) {
        const id = url.split('watch?v=')[1].split(/[?&]/)[0];
        return 'https://www.youtube.com/embed/' + id + '?autoplay=1&mute=0';
      }
      // already embed
      if (url.includes('/embed/')) return url;
      return url;
    } catch (e) {
      return url;
    }
  }

  function ensureIframe() {
    if (!iframe) {
      iframe = document.createElement('iframe');
      iframe.width = 0;
      iframe.height = 0;
      iframe.style.border = '0';
      iframe.setAttribute('allow', 'autoplay; encrypted-media');
      ytContainer.appendChild(iframe);
    }
  }

  function updatePlayer(url) {
    if (!url) {
      if (iframe) iframe.src = '';
      return;
    }
    ensureIframe();
    iframe.src = toEmbedUrl(url);
  }

  if (select) {
    select.addEventListener('change', function () {
      const url = select.value;
      if (playing) {
        updatePlayer(url);
      }
    });
  }

  if (toggleBtn) {
    toggleBtn.addEventListener('click', function () {
      playing = !playing;
      if (icon) icon.className = playing ? 'bi bi-volume-up' : 'bi bi-volume-mute';
      if (!playing) {
        // pausar: quitar src
        if (iframe) iframe.src = '';
      } else {
        // reanudar con la selección actual
        const url = select ? select.value : '';
        updatePlayer(url);
      }
    });
  }

  // función para abrir un modal por defecto (usa #loginModal si no hay otro)
  function defaultModal() {
    const modalEl = document.getElementById('loginModal');
    if (modalEl && window.bootstrap && window.bootstrap.Modal) {
      const modal = new bootstrap.Modal(modalEl);
      modal.show();
    } else {
      // fallback: intentar disparar el atributo data-bs-target del botón
      const btn = document.getElementById('loginBtn');
      if (btn) btn.click();
    }
  }

  // Exponer globalmente para mantener compatibilidad con onclick inline
  window.defaultModal = defaultModal;
});
