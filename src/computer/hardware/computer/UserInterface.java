package computer.hardware.computer;

import computer.hardware.components.Component;
import computer.hardware.components.ComponentType;
import computer.hardware.components.Monitor;
import computer.hardware.components.drive.Drive;
import computer.hardware.components.drive.HDDDrive;
import computer.hardware.components.headphones.Headphones;
import computer.hardware.components.headphones.HeadphonesAPI;
import computer.hardware.components.usbdevice.MemoryStick;
import computer.hardware.components.usbdevice.Mouse;
import computer.software.file.image.GIFImageFile;
import computer.software.file.image.JPGImageFIle;
import computer.software.file.music.MP3File;
import computer.software.file.shared.Capacity;
import computer.utils.ConsoleReader;

import java.util.Scanner;

public class UserInterface {
    private static final ComputerAPI computer = Computer.getInstance();
    private static final ConsoleReader consoleReader = ConsoleReader.getInstance();

    private static Monitor monitor = new Monitor("Dell");
    private static Drive hddDrive = new HDDDrive("HDDDrive", Capacity.GB64);
    private static Mouse mouse = new Mouse("Mysz");
    private static MemoryStick memoryStick = new MemoryStick("Pendrive", Capacity.GB1);

    private static HeadphonesAPI headphones= new Headphones();

    private static MenuOption menuOption;
    private static ComponentType componentType;

    public static void displayInterface() {
        computerBootstrap();

        do {
            System.out.println("""
                    Choose the submenu
                    1. USB devices
                    2. Files
                    3. Hardware
                    4. end <- to exit
                    """);
            menuOption = MenuOption.chosenAction(consoleReader.readFromConsole().nextLine(), MenuIndicator.MAIN_MENU);

            switch (menuOption) {

                case USB_DEVICES_MENU -> usbDeviceMenu();
                case FILES_MENU -> fileMenu();
                case HARDWARE_MENU -> hardwareMenu();
            }
        } while (!menuOption.equals(MenuOption.END));
    }

    private static void usbDeviceMenu(){
        do {
            System.out.println("""
                                Choose the option
                                1. Add USB device
                                2. Remove USB device
                                3. List USB devices
                                back <- to go back
                                end <- to exit
                                """);
            menuOption = MenuOption.chosenAction(consoleReader.readFromConsole().nextLine(), MenuIndicator.USB_MENU);

            switch (menuOption) {
                case ADD_USB_DEVICE -> {
                    System.out.println("Adding USB device");
                }
                case REMOVE_USB_DEVICE -> {
                    System.out.println("Removing USB device");
                }
                case LIST_USB_DEVICES -> {
//                                for (USBDevice device : usbDevices) {
//                                    System.out.println(device.getName());
//                                    if (device instanceof MemoryStick) {
//                                        System.out.println(((MemoryStick) device).getStorageCapacity() + "B");
//                                    }
//                                }
                }
                case END -> {
                    System.exit(0);
                }
                default -> {
                    if (!menuOption.equals(MenuOption.BACK)) {
                        System.out.println("Wrong option");
                    }
                }
            }
        } while (!menuOption.equals(MenuOption.BACK));
    }

    private static void fileMenu() {
        do {
            System.out.println("""
                                Choose the option
                                1. Add file
                                2. Remove file
                                3. Find file
                                4. List all files
                                back <- to go back
                                end <- to exit
                                """);
            menuOption = MenuOption.chosenAction(consoleReader.readFromConsole().nextLine(), MenuIndicator.FILE_MENU);

            switch (menuOption) {
                case ADD_FILE -> {
                    System.out.println("Adding file");
                }
                case REMOVE_FILE -> {
                    System.out.println("Removing file");
                }
                case FIND_FILE -> {
                    System.out.println("Finding file");
                }
                case LIST_ALL_FILES -> {
                    computer.listFiles();
                }
                case END -> {
                    System.exit(0);
                }
                default -> {
                    if (!menuOption.equals(MenuOption.BACK)) {
                        System.out.println("Wrong option");
                    }
                }
            }
        } while (!menuOption.equals(MenuOption.BACK));
    }

    private static void hardwareMenu() {
        do {
            System.out.println("""
                                Choose the option
                                1. Add hardware
                                2. Remove hardware
                                3. List hardware
                                4. Set high monitor resolution
                                5. Set low monitor resolution
                                6. Change headphone's volume
                                7. Show current headphone's volume
                                back <- to go back
                                end <- to exit
                                """);
            menuOption = MenuOption.chosenAction(consoleReader.readFromConsole().nextLine(), MenuIndicator.HARDWARE_MENU);

            switch (menuOption) {
                case ADD_HARDWARE -> {
                    System.out.println("Adding hardware");
                }
                case REMOVE_HARDWARE -> removeComponent();
                case LIST_HARDWARE -> listHardware();
                case SET_HIGH_MONITOR_RESOLUTION -> setHighMonitorResolution();
                case SET_LOW_MONITOR_RESOLUTION -> setLowMonitorResolution();
                case CHANGE_HEADPHONE_VOLUME -> changeHeadphonesVolume();
                case SHOW_CURRENT_HEADPHONE_VOLUME -> showHeadphonesVolume();
                case END -> System.exit(0);

                default -> {
                    if (!menuOption.equals(MenuOption.BACK)) {
                        System.out.println("Wrong option");
                    }
                }
            }
        } while (!menuOption.equals(MenuOption.BACK));
    }

    private static void removeComponent() { // TODO: rozwinąć tą metodę do wybierania jednego z pośród wszystkich podłączonych, wykorzystać do tego switch-case. Na ten moment sposób działania wymaga poprawnego wprowadzenia danych przez użytkownika
        listHardware();
        System.out.println("Podaj typ urządzenia które chcesz usunąć: ");
        componentType = ComponentType.valueOf(consoleReader.readFromConsole().nextLine().toUpperCase());

        computer.removeComponent(computer.getComponent(componentType));
    }

    private static void listHardware() {
        computer.getComponents().forEach(component -> System.out.println(component.getName() + " \t" + component.getType()));
    }

    private static void setHighMonitorResolution() {
        Component component = computer.getComponent(ComponentType.MONITOR);
        Monitor monitor1 = (Monitor) component;

        monitor1.setHeightResolution();
    }

    private static void setLowMonitorResolution() {
        Component component = computer.getComponent(ComponentType.MONITOR);
        Monitor monitor1 = (Monitor) component;

        monitor1.setLowResolution();
    }

    private static void changeHeadphonesVolume() {
        Component component = computer.getComponent(ComponentType.MONITOR);
        headphones = (Headphones) component;

        headphones.changeVolume();
    }

    private static void showHeadphonesVolume() {
        Component component = computer.getComponent(ComponentType.MONITOR);
        headphones = (Headphones) component;

        headphones.showVolume();
    }

    private static void computerBootstrap() {   // ta metoda pozwala na wstępną konfiguracji komputera bez konieczności robienia tego każdorazowo od zera z poziomu ui
        computer.addComponent(monitor);
        computer.addComponent(hddDrive);
        computer.addComponent(mouse);
        computer.addComponent(memoryStick);

        MP3File mp3File = new MP3File("audio.mp3", 4000, "Rammstein", "Sonne", 100);
        var gifImageFile = new GIFImageFile("funnydog.gif", 150);
        JPGImageFIle jpgImageFIle = new JPGImageFIle("holidays.jpg", 400, 80);

        computer.addFile(mp3File);
        computer.addFile(gifImageFile);
        computer.addFile(jpgImageFIle);
    }
}
