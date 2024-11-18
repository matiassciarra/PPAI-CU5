import React from "react";

const VinosListado = ({ vinos }) => (
  <div className="col-6">
    <div className="header-container p-3 mb-2 bg-primary text-white rounded d-flex">
      <h2 className="mb-0">Resumen vinos</h2>
    </div>

    <div style={{ maxHeight: "550px", overflowY: "auto" }}>
      {vinos.length > 0 ? (
        vinos.map((vino, index) => (
          <div key={index} className="card sm-4 p-2">
            {vino.creado ? (
              <h4 className="text-success">"Vino creado"</h4>
            ) : (
              <h4 className="text-warning"> "Vino actualizado"</h4>
            )}
            <p>
              <b>Nombre:</b> {vino.nombre}
            </p>
            <p>
              <b>Añada:</b> {vino.añada}
            </p>
            <p>
              <b>Bodega:</b> {vino.nombreBodega}
            </p>

            <p>
              <b>Nota de cata:</b> {vino.notaDeCataBodega}
            </p>

            <p>
              <b>Precio:</b> ${vino.precio}
            </p>

            <p>
              <b>Composición:</b>
            </p>
            {vino.varietales.map((varietal, idx) => (
              <div>
                <p key={idx}>
                  •{varietal.nombreUva}: {varietal.porcentajeComposicion}%
                </p>
              </div>
            ))}

            <p>
              <b>Fecha de actualización:</b> {vino.fechaActualizacion}
            </p>
          </div>
        ))
      ) : (
        <p>No hay vinos cargados</p>
      )}
    </div>
  </div>
);

export default VinosListado;
