package computer.hardware.computer;

import computer.hardware.components.Components;
import computer.hardware.components.Headphones;
import computer.hardware.components.Monitor;
import computer.hardware.components.drive.Drive;
import computer.hardware.components.usbdevice.MemoryStick;
import computer.software.file.shared.File;
import computer.hardware.components.usbdevice.USBDevice;
import computer.software.file.shared.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    List<Components> components = new ArrayList<>();

    private static Computer instance;

    private Computer(Monitor monitor, Drive drive) {
        components.add(monitor);
        components.add(drive);
        // run();   dodać metodę uruchamiającą komputer, jeżeli będzie w konstruktorze zostanie automatycznie wywołana podczas inicializowania instancji Computer
    }

    public static Computer getInstance(Monitor monitor, Drive drive){
        if (instance == null){
            return new Computer(monitor, drive);
        } else return instance;
    }

    public Monitor getMonitor() {
        for (Components component : components) {
            if (component instanceof Monitor) {
                return (Monitor) component;
            }
        }
        throw new RuntimeException("No component found");
    }

    public Headphones getHeadphones() {
        for (Components component : components) {
            if (component instanceof Headphones) {
                return (Headphones) component;
            }
        }
        throw new RuntimeException("No component found");
    }

    public List<USBDevice> getUSBDevices() {
        List<USBDevice> usbDevices = new ArrayList<>();
        for (Components component : components) {
            if (component instanceof USBDevice) {
                usbDevices.add((USBDevice) component);
            }
        }
        return usbDevices;
    }

    public MemoryStick getMemoryStickByName(String name){
        List<USBDevice> usbDevices = getUSBDevices();
        for (USBDevice usbDevice : usbDevices) {
            if (usbDevice instanceof MemoryStick) {
                if ( usbDevice.getName().equals(name) ) {
                    return (MemoryStick) usbDevice;
                }
            }
        }
        throw new RuntimeException("Memory stick '" + name + "' device not found");
    }

    public void addComponent(Components component) {
        components.add(component);
    }

    public void removeComponent(Components component){
        components.remove(component);
    }

    public void addFile(File file){ //TODO metody działające na plikach trzeba dostosować do możliwości posiadania różnych nośników (twardy dysk, pendrive)
        var drive = getDrive();
        drive.addFile(file);
        components.add(drive);
    }

    public void listFiles(){
        var drive = getDrive();
        drive.listFiles();
    }

    public File findFile(String fileName) {
        var drive = getDrive();
        try {
            return drive.findFile(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Drive getDrive() {
        for (Components component : components) {
            if (component instanceof Drive) {
                return (Drive) component;
            }
        }
        throw new RuntimeException("No component found");
    }
}
