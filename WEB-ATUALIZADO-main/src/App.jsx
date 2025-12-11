import "./index.css";
import DeviceForm from "./DeviceForm";
import DeviceList from "./DeviceList";
import TestLog from "./TestLog";

export default function App() {
  return (
    <div className="container">
      <h2>Monitoramento de Dispositivos</h2>

      <DeviceForm />
      <DeviceList />
      <TestLog />
    </div>
  );
}
