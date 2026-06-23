function crearSidebar(){

    /* Detecta el archivo actual a partir de la URL para poder
       marcar el link correspondiente como activo. */
    const paginaActual = window.location.pathname.split('/').pop() || 'dashboard.html';

    function claseLink(pagina){
        return paginaActual === pagina
            ? 'nav-link active fw-bold text-white bg-success bg-opacity-75' // Estilo Bootstrap resaltado
            : 'nav-link text-dark';
    }

    return `
    <div class="sidebar p-3 d-flex flex-column justify-content-between" style="min-height: 100vh; background-color: #f8f9fa; border-right: 1px solid #e2e8f0;">

        <div>
            <div class="logo mb-4 fs-4 fw-bold text-success text-center border-bottom pb-3">
                🌱 La Semilla
            </div>

            <ul class="nav nav-pills flex-column gap-1">
                <li class="nav-item">
                    <a href="dashboard.html" class="${claseLink('dashboard.html')}">
                        <i class="bi bi-speedometer2 me-2"></i> Dashboard
                    </a>
                </li>
                <li class="nav-item">
                    <a href="productos.html" class="${claseLink('productos.html')}">
                        <i class="bi bi-box-seam me-2"></i> Productos
                    </a>
                </li>
                <li class="nav-item">
                    <a href="compras.html" class="${claseLink('compras.html')}">
                        <i class="bi bi-cart-plus me-2"></i> Compras
                    </a>
                </li>
                <li class="nav-item">
                    <a href="ventas.html" class="${claseLink('ventas.html')}">
                        <i class="bi bi-cart-check me-2"></i> Ventas
                    </a>
                </li>
                <li class="nav-item">
                    <a href="reportes.html" class="${claseLink('reportes.html')}">
                        <i class="bi bi-bar-chart me-2"></i> Reportes
                    </a>
                </li>
            </ul>
        </div>

        <div class="pt-3 border-top">
            <button id="btn-logout" class="btn btn-outline-danger w-100 d-flex align-items-center justify-content-center gap-2" style="border-radius: 10px;">
                <i class="bi bi-box-arrow-left"></i> Cerrar Sesión
            </button>
        </div>

    </div>
    `;
}

/* Inserta el sidebar automáticamente apenas el HTML esté listo. */
document.addEventListener('DOMContentLoaded', () => {
    const contenedor = document.getElementById('sidebar-placeholder');

    if(contenedor){
        contenedor.innerHTML = crearSidebar();

        // 🎯 Lógica para el botón de Cerrar Sesión
        const botonLogout = document.getElementById('btn-logout');
        if(botonLogout) {
            botonLogout.addEventListener('click', () => {
                // Borramos los datos del usuario guardados (si usas tokens o sesión en el front)
                localStorage.clear();
                sessionStorage.clear();
                
                // Redirección al Login real (ajusta el nombre si es login.html o index.html)
                window.location.href = 'index.html'; 
            });
        }
    }
});