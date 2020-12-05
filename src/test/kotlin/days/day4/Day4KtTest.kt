package days.day4

import org.junit.Assert
import org.junit.Test

internal class Day4KtTest {
    val day4 = Day4()

    @Test
    fun testInput() {
        val result = day4(
            listOf(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
                "byr:1937 iyr:2017 cid:147 hgt:183cm"
            )
        )

        Assert.assertEquals(result.size, 1)
        Assert.assertEquals(
            result,
            listOf(
                Passport(
                    hairColor = "#fffffd",
                    countryId = "147",
                    eyeColor = "gry",
                    height = "183cm",
                    birthYear = 1937,
                    id = "860033327",
                    expirationYear = 2020,
                    issueYear = 2017
                )
            )
        )

    }
}