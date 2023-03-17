package cz.coffee.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.google.gson.JsonElement;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

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
 * Created: pondělí (13.03.2023)
 */

@Name("Json size")
@Description("Returns the size of the json")
@Examples({
        "on load:",
        "\tset {_json} to json from text \"{'E1': 1, 'E2': 2}\"",
        "\tif size of {_json} > 10",
        "\t\tsend \"size is too big\""
})
@Since("2.8.0 - performance & clean")
public class ExprSizeOfJson extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprSizeOfJson.class, Integer.class, ExpressionType.SIMPLE, "size of %json%", "%json% size");
    }

    private Expression<JsonElement> jsonElementExpression;


    @Override
    protected @Nullable Integer @NotNull [] get(@NotNull Event event) {
        final JsonElement json = jsonElementExpression.getSingle(event);
        if (json == null) return new Integer[0];
        if (json.isJsonArray()) return new Integer[]{json.getAsJsonArray().size()};
        if (json.isJsonObject()) return new Integer[]{json.getAsJsonObject().size()};
        return new Integer[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "size of " + jsonElementExpression.toString(event, b);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        jsonElementExpression = (Expression<JsonElement>) expressions[0];
        return true;
    }
}
