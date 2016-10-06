package ir.alilo.virustotalclient.utils

import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<T>.callbackWithCode(codePosition: Int = -1, callback: (Int) -> Unit) {
    thenAnswer { callback((if (codePosition == -1) it.arguments.last() else it.arguments[codePosition]) as Int) }
}
