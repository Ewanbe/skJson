package cz.coffee.core;

/**
 * This file is part of skJson.
 * <p>
 * Skript is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Skript is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Skript.  If not, see <<a href="http://www.gnu.org/licenses/">...</a>>.
 * <p>
 * Copyright coffeeRequired nd contributors
 */


@SuppressWarnings("unused")
public class Version {

    private final static int STATIC_VERSION = 1165;
    private static int serverVersion;

    public Version(String version) {
        serverVersion = Integer.parseInt(version.split("-")[0].replaceAll("[.]", ""));
    }

    public boolean isLegacy() {
        return serverVersion <= STATIC_VERSION;
    }

    public int getServerVersion() {
        return serverVersion;
    }

}