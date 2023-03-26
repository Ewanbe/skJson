package cz.coffee.skript.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.io.File;

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
 * <p>
 * Created: Sunday (3/12/2023)
 */

@Name("Json file is empty")
@Description("You can check if the json file empty")
@Examples("""
        Command jsonFileIsEmpty:
            trigger:
                if json file "plugins/raw/test.json" is empty:
                    send true
                else:
                    send false
        """
)
@Since("2.8.0 - performance & clean")

public class CondJsonEmpty extends Condition {

    static {
        Skript.registerCondition(CondJsonEmpty.class,
                "json file %string% is empty",
                "json file %string% is(n't| not) empty"
        );
    }

    private int line;
    private Expression<String> filePath;

    @Override
    public boolean check(@NotNull Event e) {
        final String fileString = filePath.getSingle(e);
        assert fileString != null;
        final File file = new File(fileString);
        return (line == 0) == file.length() < 1;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "json file " + filePath.toString(e, debug) + " " + (line == 0 ? "is" : "does not") + " empty";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        line = matchedPattern;
        filePath = (Expression<String>) exprs[0];
        return true;
    }
}
