{pkgs ? import <nixpkgs> {}}:
pkgs.mkShell {
  buildInputs = with pkgs; [
    openjdk11
    maven
    # idea.idea-ultimate # i have a license from uni
  ];
  shellHook = ''
  function run_dev {
    mvn compile quarkus:dev
  }
  '';
}
