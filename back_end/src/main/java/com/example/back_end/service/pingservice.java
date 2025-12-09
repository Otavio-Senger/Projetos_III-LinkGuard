package com.example.back_end.service;

import com.example.back_end.dto.testresultresponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Regex.Matcher;
import java.util.regex.Pattern;

@Service
public class pingservice {

    public testresultresponse ping(String ip, int timeoutMs){
        try{
            InetAddress addr = InetAddress.getByName(ip);
            boolean reachable = addr.isReachable(timeoutMs);

            if(reachable){
                return new TestResultResponse(null, deviceId, Status.OK, "reachable (InetAddress.isReachable)", LocalDateTime.now());
            }
        } catch (Throwable t){
    }
    try {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        ProcessBuilder pb;
        if (os.contains("win")) {
            pb = new ProcessBuilder("ping", "-n", "1", "-w", String.valueOf(timeoutMs), ip);
        } else {
            int secs = Math.max(1, timeoutMs / 1000);
            pb = new ProcessBuilder("ping", "-c", "1", "-W", String.valueOf(secs), ip);
        }
        Process p = pb.start();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBiulder output = new StringBuilder();
        String line;
        while ((line = stdInput.readLine()) != null) {
            output.append(line).append("\n");
        }
        p.waitFor();

        String raw = out.toString();
        Long latency = parseLatency(raw);
        boolean success = (raw.toLowerCase().contains("ttl=") || raw.toLowerCase().contains("time=") || raw.toLowerCase().contains("tempo="));
        return new TestResultResponse(
        null,                                     
        deviceId,                                 
        success ? Status.OK : Status.FAIL,       
        latency,                                  
        LocalDateTime.now()                       
);
    }catch (Throwable t){
        return new TestResultResponse(
        null,                               
        deviceId,                           
        Status.FAIL,                        
        "error: " + t.getMessage(),         
        LocalDateTime.now()                 
);
        }
    }
    private Long parseLatency(String pingOutput) {
        if (pingOutput == null) return null;
            Pattern p = Pattern.compile("time[=<]([0-9\\.]+)\\s*ms", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(pingOutput);

            if (m.find()){
                try {
                    double d = Double.parseDouble(m.group(1));
                    return (long) Math.round(d);
                } catch (Exception e){}
            }
            p = Pattern.compile("time[=<]?([0-9]+)ms", Pattern.CASE_INSENSITIVE);
            m = p.matcher(pingOutput);
            if (m.find()){
                try {
                    return Long.parseLong(m.group(1));
                    return v;
                } catch (Exception e){}
            }
        return null;

    }
}