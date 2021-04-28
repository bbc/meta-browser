require chromium-gn.inc

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS += "\
        at-spi2-atk \
        libxkbcommon \
        virtual/egl \
"

SRC_URI += "\
"

GN_ARGS += "\
        ${PACKAGECONFIG_CONFARGS} \
        use_ozone=true \
        ozone_auto_platforms=false \
        ozone_platform_headless=false \
        ozone_platform_wayland=false \
        ozone_platform_x11=false \
        ozone_platform_drm = true \
        ozone_platform = "drm" \
        use_vc4_minigbm = true \
        \
        use_v4l2_codec = true \
        use_v4l2_non_standard = false \
        use_v4l2_stateless_decoder = false \
        \
        use_xkbcommon=true \
"

# Since M87, Chromium builds Ozone by default, but continues to use X11.
# However, the goal of two recipes - Ozone/Wayland and non-Ozone/X11 is
# to provide either Wayland or X11 and avoid pulling dependencies of each
# on environments where Wayland or X11 is not actually required.
# See https://crrev.com/c/2382834
GN_ARGS += "use_x11=false"

# The chromium binary must always be started with those arguments.
CHROMIUM_EXTRA_ARGS_append = " --ozone-platform=wayland"
