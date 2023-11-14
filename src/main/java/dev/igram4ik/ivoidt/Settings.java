package dev.igram4ik.ivoidt;

import dev.igram4ik.ivoidt.commons.config.YamlConfig;
import java.util.List;

public class Settings extends YamlConfig {
    @Ignore
    public static final Settings IMP = new Settings();

    @Comment("Включена ли проверка в пустоте или нет")
    public boolean ENABLED = true;

    @Comment("Режим отладки (пригодиться если присутствуют баги или ошибки)")
    public boolean DEBUG = false;

    @Comment("Высота на которой будет работать проверка")
    public int Y_VOID = 1;
    @Comment("Мира в которых включена проверка")
    public List<String> WORLDS = List.of("world");

    @Comment("Режим работы при нахождении игрока [TELEPORT/COMMAND]")
    public Type TYPE = Type.TELEPORT;

    @Comment("Настройки телепоратции (Если стоит TELEPORT)")
    public TELEPORT TELEPORT;
    public static class TELEPORT {
        @Comment("Телепортировать ли в том же мире, в котором находится игрок?")
        public boolean SAME_WORLD = true;
        @Comment("Мир, если выключен 'same-world'")
        public String WORLD = "world";
        public int Z = 1;
        public int Y = 1;
        public int X = 1;
        public int YAW = 1;
        public int PITCH = 1;
        @Comment("Телепортировать ли рядом, если игроков слишком много в одной точке. (Зависит от GameRule MaxEntityCramming)")
        public boolean NEAR = false;
    }
    @Comment("Команды которые должны выпольняться при нахождении игрока (Если стоит COMMAND)")
    public List<String> COMMANDS = List.of("/say hello");
    @Comment("Выпольнять ли команды от лица игрока - [true] / [false] - будут выполняться от консоли")
    public boolean PSENDER = true;

    @Comment("Блэк-лист игроков которые не будут проверятся")
    public List<String> BLACKLIST = List.of("Player1");

    @Comment("Оповещать ли игрока об телепортации?")
    public boolean TP_NOTICE = false;

    @Comment("Локализация команд и логов | Сериализация цветов - MINIMESSAGE (https://webui.advntr.dev/) | Перенос строки - {NL}")
    public MESSAGES MESSAGES;
    public static class MESSAGES {
        public String RELOADING = "Перезагрузка...";
        public String SUCCESS_RELOAD = "Плагин успешно перезагрузился.";
        public String RELOAD_FAILED = "Не удалось успешно перезагрузиться.";
        public String NO_PERM = "Недостаточно прав.";
        public String NO_ARGS = "Недостаточно аргументов.";
        public String UNKNOWN_COMMAND = "Неизвестная команда.";
        public String BL_SUCCESS = "Игрок успешно добавлен в список игнорируемых.";
        public String BL_LIST = "Список игнорируемых:";
        public String BL_LIST_I = " - {player}";
        public String BL_FAIL = "Не удалось добавить в список, может он уже добавлен?";
        public String BL_REMOVED = "Игрок успешно удалён из списка.";
    }
}
