package com.easyhz.noffice.build_logic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor


@Suppress("EnumEntryName")
enum class NofficeFlavor(val appLabel: String, val applicationIdSuffix: String) {
    prod("노피스", ".prod"),
    dev("노피스 dev", ".dev")
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: NofficeFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += "build-type"
        productFlavors {
            NofficeFlavor.values().forEach { flavor ->
                create(flavor.name) {
                    dimension = "build-type"
                    flavorConfigurationBlock(this, flavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        applicationIdSuffix = flavor.applicationIdSuffix
                    }
                    manifestPlaceholders["appLabel"] = flavor.appLabel

                }
            }
        }
    }
}