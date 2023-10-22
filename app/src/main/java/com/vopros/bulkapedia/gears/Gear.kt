package com.vopros.bulkapedia.gears

import com.vopros.bulkapedia.core.Entity

data class Gear(
    override val id: String,
    val gearCell: GearCell,
    val gearSet: GearSet,
    val image: String
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "gearCell" to gearCell.name.lowercase(),
        "gearSet" to gearSet.name.lowercase(),
        "image" to image
    )

    companion object {

        val emptyGears = mapOf(
            GearCell.HEAD to "https://firebasestorage.googleapis.com/v0/b/bulkapedia-3b45a.appspot.com/o/gears%2Fdefault%2Fhead%2Fempty_head.png?alt=media&token=19388349-1aea-41de-af6c-5dfa9c344aaa",
            GearCell.BODY to "https://firebasestorage.googleapis.com/v0/b/bulkapedia-3b45a.appspot.com/o/gears%2Fdefault%2Fbody%2Fempty_body.png?alt=media&token=1452bf6d-5fbe-4b1d-8443-9d49da5976ca",
            GearCell.ARM to "https://firebasestorage.googleapis.com/v0/b/bulkapedia-3b45a.appspot.com/o/gears%2Fdefault%2Farm%2Fempty_arm.png?alt=media&token=ec01ff19-e623-4787-a8f3-94b0113f1df6",
            GearCell.LEG to "https://firebasestorage.googleapis.com/v0/b/bulkapedia-3b45a.appspot.com/o/gears%2Fdefault%2Fleg%2Fempty_leg.png?alt=media&token=24102078-05b5-4476-9c28-02a1aadbdc00",
            GearCell.DECOR to "https://firebasestorage.googleapis.com/v0/b/bulkapedia-3b45a.appspot.com/o/gears%2Fdefault%2Fdecor%2Fempty_decor.png?alt=media&token=47f11ce6-25fc-4787-8504-0858bec2d061",
            GearCell.DEVICE to "https://firebasestorage.googleapis.com/v0/b/bulkapedia-3b45a.appspot.com/o/gears%2Fdefault%2Fdevice%2Fempty_device.png?alt=media&token=1a4518a2-6258-4b81-b9ac-f69c5a40909d"
        )

    }

}
