package test.assertk.specs

import assertk.assertAll
import assertk.assertions.containsAll
import assertk.assertions.containsExactly
import assertk.assertions.containsNone
import org.assertj.core.api.Assertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class AssertSpecCollection : Spek({

    given("a collection") {

        on("containsNone()") {
            it("should pass a successful test") {
                assertk.assert(listOf(1, 2, 3, 4)).containsNone(5, 6, 7)
                assertk.assert(listOf(1, 2, 3)).containsNone(4, 5, 6, 7)
                assertk.assert(emptyList<Any?>()).containsNone(4)
                assertk.assert(listOf(3, 4)).containsNone()
                assertk.assert(listOf(1, 1.09, "awesome!", true)).containsNone(43, 1.43, "awesome")
            }

            it("should fail an unsuccessful test") {
                Assertions.assertThatThrownBy {
                    assertk.assert(listOf(1, 2, 3)).containsNone(4, 5, 6, 1)
                }.hasMessage("expected to contain none of:<[4, 5, 6, 1]> but was:<[1, 2, 3]>")

                Assertions.assertThatThrownBy {
                    assertk.assert(listOf(1, 2, 3, 4)).containsNone(8, 0, 4)
                }.hasMessage("expected to contain none of:<[8, 0, 4]> but was:<[1, 2, 3, 4]>")

                Assertions.assertThatThrownBy {
                    assertk.assert(listOf(1, 1.09, "awesome!", true)).containsNone(true, 43, "potato")
                }.hasMessage("expected to contain none of:<[true, 43, \"potato\"]> but was:<[1, 1.09, awesome!, true]>")
            }

            it("should fail an unsuccessful test with only one error message per assertion") {
                Assertions.assertThatThrownBy {
                    assertAll {
                        assertk.assert(listOf(1, 2, 3)).containsNone(5, 6, 7, 1)
                        assertk.assert(listOf("this", "is", "awesome!")).containsNone(true, 4, "awesome!")
                    }
                }.hasMessage("The following 2 assertions failed:\n"
                        + "- expected to contain none of:<[5, 6, 7, 1]> but was:<[1, 2, 3]>\n"
                        + "- expected to contain none of:<[true, 4, \"awesome!\"]> but was:<[this, is, awesome!]>")
            }
        }

        on("containsAll()") {
            it("should pass a successful test") {
                assertk.assert(listOf(1, 2, 3)).containsAll(3, 2, 1)
                assertk.assert(emptyList<Any?>()).containsAll()
                assertk.assert(listOf(1, 1.09, "awesome!", true)).containsAll(1, 1.09, "awesome!", true)
            }

            it("should fail an unsuccessful test") {
                Assertions.assertThatThrownBy {
                    assertk.assert(listOf(1, 2, 3)).containsAll(4, 3, 1, 2)
                }.hasMessage("expected to contain all:<[4, 3, 1, 2]> but was:<[1, 2, 3]>")

                Assertions.assertThatThrownBy {
                    assertk.assert(listOf(1, 2, 4, 5)).containsAll(2, 1, 3)
                }.hasMessage("expected to contain all:<[2, 1, 3]> but was:<[1, 2, 4, 5]>")

                Assertions.assertThatThrownBy {
                    assertk.assert(emptyList<Any?>()).containsAll(1, 2, 3)
                }.hasMessage("expected to contain all:<[1, 2, 3]> but was:<[]>")

                Assertions.assertThatThrownBy {
                    assertk.assert(listOf("this", "is", "awesome!")).containsAll("this", 4, "awesome!")
                }.hasMessage("expected to contain all:<[\"this\", 4, \"awesome!\"]> but was:<[this, is, awesome!]>")
            }

            it("should fail an unsuccessful test with only one error message per assertion") {
                Assertions.assertThatThrownBy {
                    assertAll {
                        assertk.assert(listOf(1, 2, 3)).containsAll(5, 6, 7)
                        assertk.assert(listOf("this", "is", "awesome!")).containsAll("this", 4, "awesome!")
                    }
                }.hasMessage("The following 2 assertions failed:\n"
                        + "- expected to contain all:<[5, 6, 7]> but was:<[1, 2, 3]>\n"
                        + "- expected to contain all:<[\"this\", 4, \"awesome!\"]> but was:<[this, is, awesome!]>")
            }
        }

        on("containsExactly()") {
            it("should pass a successful test") {
                assertk.assert(listOf(1, 2, 3)).containsExactly(1, 2, 3)
                assertk.assert(emptyList<Any?>()).containsExactly()
                assertk.assert(listOf(1, 1.09, "awesome!", true)).containsExactly(1, 1.09, "awesome!", true)
            }

            it("should fail an unsuccessful test") {
                Assertions.assertThatThrownBy {
                    assertk.assert(listOf(1, 2, 3)).containsExactly(1, 2, 3, 4)
                }.hasMessage("expected to contain exactly:<[1, 2, 3, 4]> but was:<[1, 2, 3]>")

                Assertions.assertThatThrownBy {
                    assertk.assert(listOf(1, 2, 3, 4)).containsExactly(1, 2, 3)
                }.hasMessage("expected to contain exactly:<[1, 2, 3]> but was:<[1, 2, 3, 4]>")

                Assertions.assertThatThrownBy {
                    assertk.assert(emptyList<Any?>()).containsExactly(1, 2, 3)
                }.hasMessage("expected to contain exactly:<[1, 2, 3]> but was:<[]>")

                Assertions.assertThatThrownBy {
                    assertk.assert(listOf("this", "is", "awesome!")).containsExactly("this", 4, "awesome!")
                }.hasMessage("expected to contain exactly:<[\"this\", 4, \"awesome!\"]> but was:<[this, is, awesome!]>")
            }

            it("should fail an unsuccessful test with only one error message per assertion") {
                Assertions.assertThatThrownBy {
                    assertAll {
                        assertk.assert(listOf(1, 2, 3)).containsExactly(5, 6, 7)
                        assertk.assert(listOf("this", "is", "awesome!")).containsExactly("this", 4, "awesome!")
                    }
                }.hasMessage("The following 2 assertions failed:\n"
                        + "- expected to contain exactly:<[5, 6, 7]> but was:<[1, 2, 3]>\n"
                        + "- expected to contain exactly:<[\"this\", 4, \"awesome!\"]> but was:<[this, is, awesome!]>")
            }
        }
    }
})