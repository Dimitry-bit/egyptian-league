/*
 *
 * Copyright (c) 2023 Tony Medhat
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.egyptian_league.DSA;

import java.util.ArrayList;
import java.util.List;

import com.github.egyptian_league.Utils.Pair;

public class RoundRobin {

    private RoundRobin() {
    }

    public static int rounds(int teams) {
        return (teams % 2 == 1) ? teams : teams - 1;
    }

    public static int matchPerRound(int teams) {
        int rounds = (teams % 2 == 1) ? teams : teams - 1;
        return ((rounds + 1) / 2);
    }

    public static List<ArrayList<Pair<Integer, Integer>>> circle(int teams) {
        int rounds = (teams % 2 == 1) ? teams : teams - 1;
        int mpr = (rounds + 1) / 2;

        ArrayList<Integer> t = new ArrayList<>();
        for (int i = 0; i < rounds + 1; ++i) {
            t.add(i + 1);
        }

        ArrayList<ArrayList<Pair<Integer, Integer>>> matches = new ArrayList<>(rounds);
        for (int r = 0; r < rounds; ++r) {
            matches.add(r, new ArrayList<Pair<Integer, Integer>>());

            for (int m = 0; m < mpr; ++m) {
                Pair<Integer, Integer> match = new Pair<>(t.get(m), t.get(t.size() - (1 + m)));
                matches.get(r).add(match);
            }

            t.remove((Object) (rounds - r + 1));
            t.add(1, rounds - r + 1);
        }

        return matches;
    }
}
