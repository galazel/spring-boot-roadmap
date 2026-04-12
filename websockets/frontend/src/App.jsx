import { useEffect, useRef, useState } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

function App() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const clientRef = useRef(null);

  useEffect(() => {
    const client = new Client({
      webSocketFactory: () => new SockJS("http://localhost:8080/ws"),
      reconnectDelay: 5000,

      onConnect: () => {
        console.log("connected");

        client.subscribe("/topic/greetings", (msg) => {
          setMessages((prev) => [...prev, msg.body]);
        });
      },
    });

    client.activate();
    clientRef.current = client;

    return () => {
      if (clientRef.current) {
        clientRef.current.deactivate();
      }
    };
  }, []);

  const sendMessage = () => {
    if (clientRef.current && input) {
      console.log("save");
      clientRef.current.publish({
        destination: "/app/chat",
        body: input,
      });
      setInput("");
    }
  };

  return (
    <div>
      <h2>WebSocket Chat</h2>

      {messages.map((m, i) => (
        <p key={i}>{m}</p>
      ))}

      <input value={input} onChange={(e) => setInput(e.target.value)} />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
}

export default App;
