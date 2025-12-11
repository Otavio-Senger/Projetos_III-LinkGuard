import { useState } from "react";

export default function TestLog() {
  const [id, setId] = useState("");
  const [logs, setLogs] = useState([]);

  async function loadLogs() {
    try {
      const res = await fetch(`http://localhost:8080/devices/${id}/logs`);

      if (!res.ok) {
        alert("Erro ao carregar logs");
        return;
      }

      const data = await res.json();
      setLogs(data);
    } catch (e) {
      alert("Erro ao conectar com o servidor");
    }
  }

  return (
    <div>
      <h3>Logs de Teste</h3>

      <input
        placeholder="ID do dispositivo"
        value={id}
        onChange={e => setId(e.target.value)}
      />

      <button onClick={loadLogs}>Carregar Logs</button>

      {logs.map(log => (
        <div className="box" key={log.id}>
          <strong>Status:</strong> {log.status} <br />
          <strong>Latência:</strong> {log.latency} <br />
          <strong>Data:</strong> {log.timestamp}
        </div>
      ))}
    </div>
  );
}
